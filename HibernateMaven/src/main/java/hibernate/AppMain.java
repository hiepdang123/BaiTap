package hibernate;

import java.util.Date;

import repository.UserRepository;
 
public class AppMain {
 
     
    public static void main(String[] args) {
        UserRepository userRepository= new UserRepository();
        userRepository.edit(3, "Nguyen Van Binh", "Create By 1", new Date());
        userRepository.delete(5);
    }
}
