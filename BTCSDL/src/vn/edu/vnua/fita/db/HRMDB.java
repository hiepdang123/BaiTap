package vn.edu.vnua.fita.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.edu.vnua.fita.config.DBConnection;
import vn.edu.vnua.fita.credit.CreditRule;
import vn.edu.vnua.fita.credit.JavaSubject;
import vn.edu.vnua.fita.credit.Student;
import vn.edu.vnua.fita.credit.Subject;

public class HRMDB {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection conn;

	// Viết contructor cho phép tạo đối tượng với các tham số cần thiết
	public HRMDB(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public boolean addStudent(Student student) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "INSERT INTO SINHVIEN (MaSV,HoTen,DiaChi,Lop) VALUES(?, ?, ?,?)";
		PreparedStatement pst = null;

		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setString(1, student.getCode());
			pst.setString(2, student.getFullname());
			pst.setString(3, student.getAddress());
			pst.setString(4, student.getClass_());
			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("Loi them sv: " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}

	public boolean updateStudent(Student student) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "UPDATE SINHVIEN SET HoTen = ?, DiaChi = ?, Lop=? " +

				"WHERE MaSV = ?";

		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setString(1, student.getFullname());
			pst.setString(2, student.getAddress());
			pst.setString(3, student.getClass_());
			pst.setString(4, student.getCode());
			int i = pst.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}

	public boolean deleteStudent(String studentCode) {
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		String sqlCommand = "DELETE FROM SINHVIEN WHERE MaSV = ?";
		PreparedStatement pst = null;
		boolean result = false;
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setString(1, studentCode);
			int i = pst.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}

	public List<Student> getStudentList() {
		List<Student> studentList = new ArrayList<>();
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
			statement = conn.createStatement();
			rs = statement.executeQuery("Select * from SINHVIEN");
			String studentCode;
			String fullname;
			String class_;
			String address;
			// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet

			while (rs.next()) {
				studentCode = rs.getString("MaSV");
				fullname = rs.getString("HoTen");
				class_ = rs.getString("Lop");
				address = rs.getString("DiaChi");
				// Thêm vào list
				studentList.add(new Student(studentCode, fullname, class_, address));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return studentList;
	}

	public boolean addSubject(Subject subject) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "INSERT INTO MONHOC (MaMH,TenMH,SoTC) VALUES(?, ?, ?)";
		PreparedStatement pst = null;

		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setString(1, subject.getSubjectCode());
			pst.setString(2, subject.getSubjectName());
			pst.setInt(3, subject.getCredit());
			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("Loi them mh: " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}

	// Bài tập 1: Viết chương trình cho phép SV nhập mã sinh viên, mã môn học, để
	// đăng ký vào CSDL (thêm vào bảng KetQua)
	public boolean insertResult(String maSV, String maMH, float diem) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "INSERT INTO KETQUA (MaSV,MaMH,Diem) VALUES(?, ?, ?)";
		PreparedStatement pst = null;

		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setString(1, maSV);
			pst.setString(2, maMH);
			pst.setFloat(3, diem);
			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("Loi them ket qua: " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}

	// Bài tập 2: Viết chương trình cho phép SV nhập mã sinh viên, mã môn học, điểm
	// để cập nhật vào CSDL, nếu chưa tồn tại thì báo lỗi
	public boolean updateResult(String maSV, String maMH, float diem) {
		// Tạo kết nối database với 3 tham số truyền vào
		conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
		// Tạo câu truy vấn kiểu prepare
		String sqlCommand = "UPDATE KETQUA SET Diem=? WHERE MaSV=? AND MaMH=?";
		PreparedStatement pst = null;

		boolean result = false;
		try {
			// Tạo đối tượng truy vấn kiểu Prepare
			pst = conn.prepareStatement(sqlCommand);
			// Đưa dữ liệu vào các vị trí dấu hỏi (?)
			pst.setFloat(1, diem);
			pst.setString(2, maSV);
			pst.setString(3, maMH);

			// Thực hiện chạy câu truy vấn
			int i = pst.executeUpdate();
			// Nếu thành công, 1 bản ghi được thêm vào
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("Loi cap nhat ket qua: " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally { // Đóng kết nối trong khối finally (bắt buộc chạy)
			DBConnection.closePreparedStatement(pst);
			DBConnection.closeConnect(conn);
		}
		return result;
	}

	// Bài tập 3: Thêm bảng MonHoc_KyHoc chứa thông tin mã môn học, mã kỳ học
	// (1,2...) Viết phương thức với tham số là MSV, trả về đối tượng SV gồm các
	// thông tin của sinh viên và danh sách tất cả các môn học mà sinh viên này đã
	// học qua
	public Student getStudentAndSubject(String maSV) {
		Student student = new Student();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
			String query = "Select * from SINHVIEN Inner Join KETQUA ON KETQUA.MaSV=SINHVIEN.MaSV INNER JOIN MONHOC_KYHOC ON MONHOC_KYHOC.MaMH=KETQUA.MaMH Inner Join MONHOC ON KETQUA.MaMH=MONHOC.MaMH WHERE SINHVIEN.MaSV=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, maSV);
			rs = statement.executeQuery();
			// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet

			while (rs.next()) {
				String code = rs.getString("MaSV");
				String fullname = rs.getString("HoTen");
				String class_ = rs.getString("Lop");
				String address = rs.getString("DiaChi");
				student.setCode(code);
				student.setAddress(address);
				student.setFullname(fullname);
				student.setClass_(class_);

				String subjectCode = rs.getString("MaMH");
				String subjectName = rs.getString("TenMH");
				int credit = rs.getInt("SoTC");
				float point = rs.getFloat("Diem");

				CreditRule subject = new JavaSubject(subjectCode, subjectName, credit);
				subject.setAttendanceMark(point);

				String semester = rs.getString("KyHoc");
				student.addSubject(semester, subject);
			}
		} catch (SQLException e) {
			System.out.println("Loi getStudentAndSubject: " + e.getLocalizedMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		return student;
	}

	// Bài tập 4: Viết phương thức trả về danh sách tất cả các đối tượng sinh viên
	// chứa thông tin sinh viên, các môn học qua các kỳ của SV đó
	public List<Student> getAllStudent() {
		Map<String, Student> mapStudent = new HashMap<String, Student>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
			String query = "Select * from SINHVIEN Inner Join KETQUA ON KETQUA.MaSV=SINHVIEN.MaSV INNER JOIN MONHOC_KYHOC ON MONHOC_KYHOC.MaMH=KETQUA.MaMH Inner Join MONHOC ON KETQUA.MaMH=MONHOC.MaMH";
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			// Duyệt qua danh sách các bản thi nhận được trong đối tượng ResultSet
			Student student = new Student();
			while (rs.next()) {
				String code = rs.getString("MaSV");

				String fullname = rs.getString("HoTen");
				String class_ = rs.getString("Lop");
				String address = rs.getString("DiaChi");
				if (!code.equals(student.getCode())) {
					student = new Student();
					student.setCode(code);
					student.setAddress(address);
					student.setFullname(fullname);
					student.setClass_(class_);
				}

				String subjectCode = rs.getString("MaMH");
				String subjectName = rs.getString("TenMH");
				int credit = rs.getInt("SoTC");
				float point = rs.getFloat("Diem");

				CreditRule subject = new JavaSubject(subjectCode, subjectName, credit);
				subject.setAttendanceMark(point);

				String semester = rs.getString("KyHoc");
				student.addSubject(semester, subject);
				mapStudent.put(code, student);
			}
		} catch (SQLException e) {
			System.out.println("Loi getStudentAndSubject: " + e.getLocalizedMessage());
		} finally {
			DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(conn);
		}
		List<Student> list = new ArrayList<Student>(mapStudent.values());
		return list;
	}

	public List<Student> selectAllStudent() {
		List<Student> studentListList = null;
		Map<String, Student> studentMap = new HashMap<String, Student>();
		String SELECT_ALL_USERS_SQL = "Select * from SINHVIEN Inner Join KETQUA ON KETQUA.MaSV=SINHVIEN.MaSV INNER JOIN MONHOC_KYHOC ON MONHOC_KYHOC.MaMH=KETQUA.MaMH Inner Join MONHOC ON KETQUA.MaMH=MONHOC.MaMH";
		try (Connection connection = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
				Statement statement = connection.createStatement()) {

			ResultSet rs = statement.executeQuery(SELECT_ALL_USERS_SQL);

			// Duyệt danh sach bản ghi trả về
			while (rs.next()) {
				/*
				 * String username = rs.getString("MaSV"); String password =
				 * rs.getString("Hodem"); System.out.println(rs.getString("MaSV") + "," +
				 * rs.getString("TenMH") + " " + rs.getString("Diem"));
				 */
				Student student = new Student();
				student.setCode(rs.getString("MaSV"));
				student.setFullname(rs.getString("HoTen"));

				if (studentMap.containsKey(rs.getString("MaSV"))) { // Neu đa có sv đó rồi thì cập nhật ds môn học của
																	// sv đó
					JavaSubject sub = new JavaSubject(rs.getString("MaMH"), rs.getString("TenMH"), rs.getInt("soTc"));
					student.addSubject(rs.getString("KyHoc"), sub);
					System.out.println(student);
				} else { // nếu chưa có thì thêm sv vào map
					studentMap.put(rs.getString("MaSV"), student);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		studentListList = new ArrayList<Student>(studentMap.values()); // Chuyển từ Map về List

		return studentListList;
	}
}
