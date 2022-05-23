import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = org.apache.commons.io.FileUtils.getFile("D:\\Program Files\\apache-maven-3.6.3\\lib\\commons-io-2.5.jar");
        String fileName = file.getName();
        System.out.println("Hello world!");
    }
}