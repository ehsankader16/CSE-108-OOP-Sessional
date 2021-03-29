public class Department {

    private int id;
    private String name;
    private Employee [] employees;
    private int employeeCount;
    // add your code here
    // there can be at most 20 departments
    private static int departmentCount;
    private static Department departments[]=new Department[20];

    // you are not allowed to write any other constructor
    public Department(int id, String name) {

        if(departmentCount==20){
            System.out.println("You can't add anymore department, number of department has already reached its limit of 20 ");
        }
        else{
            this.id = id;
            this.name = name;
            this.employees = new Employee[10];
            departments[departmentCount++]=this;
        }
        // add your code here
    }

    public void addEmployee(Employee employee){
        if(employeeCount==10){
            System.out.println("You can't add anymore employee, number of employee has already reached its limit of 10 ");
            return;
        }
        employees[employeeCount++]=employee;
    }

    public double getDepartmentSalary(){
        double departmentSalary=0;
        for(int i=0;i<employeeCount;i++){
            departmentSalary+=employees[i].getSalary();
        }
        return departmentSalary;
    }

    public Employee getMaxSalaryEmployee(){
        if(employeeCount==0){
            return null;
        }
        Employee employee=employees[0];
        for(int i=1;i<employeeCount;i++){
            if(employees[i].getSalary()>employee.getSalary()){
                employee=employees[i];
            }
        }
        return employee;
    }

    public static double getTotalSalary(){
        double totalSalary=0;
        for(int i=0;i<departmentCount;i++){
            for(int j=0;j<departments[i].employeeCount;j++){
                totalSalary+=departments[i].employees[j].getSalary();
            }
        }
        return totalSalary;
    }
    // add your code here
}