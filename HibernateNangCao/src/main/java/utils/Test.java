package utils;

import java.util.List;

import entity.User;
import repository.UserRepo;

public class Test {
	public static void main(String[] args) {
		UserRepo userRepo=new UserRepo();
//		List<User> list=userRepo.getAllUser();
//		for (User user : list) {
//			System.out.println(user);
//		}
//		System.out.println(userRepo.getById(2));
//		System.out.println(userRepo.orderUserByCreatedAt());
//		System.out.println(userRepo.groupByUserByCreatedAt());
		boolean isSuccess=false;
//		isSuccess=userRepo.updateUser(3, "update user 3", "1234512");
//		if(isSuccess) {
//			System.out.println("Sửa thành công");
//		}else {
//			System.out.println("Sửa thất bại");
//		}
//		isSuccess=userRepo.deleteUser("12");
//		if(isSuccess) {
//			System.out.println("Xóa thành công");
//		}else {
//			System.out.println("Xóa thất bại");
//		}
//		isSuccess=userRepo.insertUser();
//		if(isSuccess) {
//			System.out.println("Thêm thành công");
//		}else {
//			System.out.println("Thêm thất bại");
//		}
		List<User> list=userRepo.pagingUser();
		System.out.println(list);
	}
}
