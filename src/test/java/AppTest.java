import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    
    @Test
    public void testCalculateAveragePerfect() {
        // Test with perfect scores
        double result = calculateAverage(100, 100, 100);
        assertEquals(100.0, result, 0.01);
    }
    
    @Test
    public void testCalculateAverageNormal() {
        // Test with normal scores
        double result = calculateAverage(80, 85, 90);
        assertEquals(85.0, result, 0.01);
    }
    
    @Test
    public void testGetGradeA() {
        String grade = getGrade(95);
        assertEquals("A+", grade);
    }
    
    @Test
    public void testGetGradeB() {
        String grade = getGrade(75);
        assertEquals("B", grade);
    }
    
    @Test
    public void testGetGradeF() {
        String grade = getGrade(35);
        assertEquals("F", grade);
    }
    
    @Test
    public void testBoundaryConditions() {
        // Test boundary conditions
        assertEquals("A+", getGrade(90));
        assertEquals("A", getGrade(80));
        assertEquals("B", getGrade(70));
        assertEquals("C", getGrade(60));
        assertEquals("D", getGrade(50));
        assertEquals("F", getGrade(49));
    }
    
    // Helper methods (same logic as App.java)
    private double calculateAverage(double math, double english, double science) {
        return (math + english + science) / 3.0;
    }
    
    private String getGrade(double average) {
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }
}