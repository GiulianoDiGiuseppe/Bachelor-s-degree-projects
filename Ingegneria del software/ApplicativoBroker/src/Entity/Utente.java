
package Entity;

public class Utente {


     public static enum UserRole { RISPARMIATORE,BROKER };

     public Utente(String username, String password, UserRole UserRole) {
         this.username = username;
         this.password = password;
         this.userRole = UserRole;
     }


       public UserRole get_UserRole() {
            return userRole;
        }

        public void set_UserRole(UserRole userRole) {
            this.userRole = userRole;
        }

        public String get_Password() {
            return password;
        }

        public void set_Password(String password) {
            this.password = password;
        }


        public String get_Username() {
            return username;
        }

        public void set_Username(String username) {
            this.username = username;
        }

        private UserRole userRole;
        private String username;
        private String password;




}