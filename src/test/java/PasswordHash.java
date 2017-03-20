import junit.framework.TestCase;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash extends TestCase {

    private static final String PASSWORD = "wick";
    private static final Object SALT = null;
    private static final String MD5_EXPECTED_HASH = "9708d66b9338e4ee6f5db99a65f202de";
    private static final String BCRYPT_EXPECTED_HASH = "$2a$10$.Yrz3JNjPW6oKy2dZRW.4OiyCvBOzcnZje5pjaUesLb.aF5zOJfZO";

    public void testMD5Hash() {
        Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        final String hashedPassword = passwordEncoder.encodePassword(PASSWORD, SALT);
        assertEquals(MD5_EXPECTED_HASH, hashedPassword);
    }

    public void testBCryptHash() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String hashedPassword = passwordEncoder.encode(PASSWORD);
        System.out.println("hashedPassword = " + hashedPassword);
        assertNotNull(hashedPassword);
    }

}
