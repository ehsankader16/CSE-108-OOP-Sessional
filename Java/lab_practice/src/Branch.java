
public class Branch {

    private int id;
    private String name;
    private Account []  accounts;
    private int accountCount;

    private static int branchCount;
    private static Branch branches[]=new Branch[20];
    // add your code here
    // there can be at most 20 branches

    // you are not allowed to write any other constructor
    public Branch(int id, String name) {

        if(branchCount==20){
            System.out.println("There can't be more than 20 branch");
        }
        else{
            this.id = id;
            this.name = name;
            this.accounts = new Account[10];
            branches[branchCount]=this;
            branchCount++;
        }
        // add your code here
    }

    private int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void addAccount(Account a) {
        if(accountCount==10){
            System.out.println("This branch has reached its account limit");
        }
        accounts[accountCount++] = a;

    }

    public double getBranchBalance() {
        double branchBalance=0;
        for(Account account:accounts){
            if(account==null)
                break;
            branchBalance+=account.getBalance();
        }
        return branchBalance;
    }

    public Account getMinBalanceAccount() {
        if(accountCount==0){
            System.out.println("There is no account in this branch");
            return null;
        }
        Account minBalanceAccount=accounts[0];
        for(int i=1;i<accountCount;i++){
            if(accounts[i].getBalance()<minBalanceAccount.getBalance())
                minBalanceAccount=accounts[i];
        }
        return minBalanceAccount;
    }

    public static void transferBalance(Account from, Account to,double amount){
        from.setBalance(from.getBalance()-amount);
        to.setBalance(to.getBalance()+amount);
    }


    public static void printAllBranchesInfo(){
        for(Branch branch:branches){
            if(branch==null){
                break;
            }
            System.out.println("Branch ID: "+branch.getId()+", Branch Name: "+branch.getName());
            for(Account account:branch.getAccounts()){
                if(account==null){
                    break;
                }
                System.out.println("Account Number: "+account.getNumber()+" Customer Name: "+account.getCustomer()+" Balance: "+account.getBalance());
                //System.out.println(account);
            }
        }
    }

}