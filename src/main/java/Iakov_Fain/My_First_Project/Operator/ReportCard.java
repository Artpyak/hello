package Iakov_Fain.My_First_Project.Operator;


public class ReportCard {
    public char convertGrades(int testResult) {
        char grade;
        if (testResult >= 90) {
            grade = 'A';
        } else if (testResult >= 80 && testResult < 90) {
            grade = 'B';
        } else if (testResult >= 70 && testResult < 80) {
            grade = 'C';
        } else {
            grade = 'D';
        }
        return grade;
    }


        public static void main(String[] args) {
            ReportCard rc = new ReportCard();
            char yourGrade = rc.convertGrades(92);
            System.out.println("Ваша первая оценка " + yourGrade);
            yourGrade = rc.convertGrades(55);
            System.out.println("Ваша вторая оценка " + yourGrade);
        }

}
