# include <iostream>
using namespace std;

# define INTEGER 1
# define INT_ARRAY 2
# define INT_MATRIX 3

// Container class can contain an integer, or an integer array or an integer matrix, but exactly one of them
class Container{
    // Do not add any additional member variable
    int *value;
    int *valueArray;
    int **valueMatrix;
    int firstDim, secondDim;    // the dimensions of array/matrix, in case of single integer, both should be 0
    int storedType;

    // the following is a private method, not needed from outside
    void reset(){
        if (value != NULL){
            // free memory occupied by value
            delete value;
        }
        if (valueArray != NULL){
            // free memory occupied by valueArray
            delete []valueArray;
        }
        if (valueMatrix != NULL){
            // free memory occupied by valueMatrix
            int i;
            for(i=0;i<firstDim;i++)
                delete[] valueMatrix[i];
            delete valueMatrix;
        }

        firstDim = 0;
        secondDim = 0;
        storedType = -1;
    }

public:
    // do not write any additional public method except for those which you are asked to, according to the comments
    Container(){
        cout << "Constructing Container with empty parameter" << endl;
        cout << "___________________________________________" << endl;
        value = NULL;
        valueArray = NULL;
        valueMatrix = NULL;
        firstDim = 0;
        secondDim = 0;
        storedType = -1;
    }

    Container (int val){
        cout << "Constructing Container with a single integer parameter" << endl;
        cout << "______________________________________________________" << endl;
        // dynamically allocate memory to the appropriate pointer and initialize it with the argument(s) of this constructor
        value=new int;
        valueArray = NULL;
        valueMatrix = NULL;
        *value=val;
        firstDim = 0;
        secondDim = 0;
        storedType = INTEGER;
    }

    Container (int *valArr, int len){

        cout << "Constructing Container with integer array parameter" << endl;
        cout << "___________________________________________________" << endl;
        // dynamically allocate memory to the appropriate pointer and initialize with the argument(s) of this constructor
        valueArray=new int[len];
        value = NULL;
        valueMatrix = NULL;

        int i;
        for(i=0;i<len;i++){
            valueArray[i]=valArr[i];
        }
        firstDim = len;
        secondDim = 0;
        storedType = INT_ARRAY;
    }

    Container (int **valMat, int r, int c){
        cout << "Constructing Container with integer matrix parameter" << endl;
        cout << "___________________________________________________" << endl;

        valueMatrix = new int*[r];
        value = NULL;
        valueArray = NULL;

        int i,j;
        for(i=0;i<r;i++){
            valueMatrix[i]=new int[c];
            for(j=0;j<c;j++){
                valueMatrix[i][j]=valMat[i][j];
            }
        }

        firstDim = r;
        secondDim = c;
        storedType = INT_MATRIX;
    }

    // write a copy constructor whose first two lines should be as follows:
    // cout << "Calling copy constructor of Container" << endl;
    // cout << "_____________________________________" << endl;
    Container(const Container &obj){
        cout << "Calling copy constructor of Container" << endl;
        cout << "_____________________________________" << endl;

        value=NULL;
        valueArray=NULL;
        valueMatrix=NULL;
        firstDim = obj.firstDim;
        secondDim = obj.secondDim;
        storedType = obj.storedType;

        if(storedType==INTEGER){
            value=new int;
            *value=*(obj.value);
        }
        else if(storedType==INT_ARRAY){
            int len;
            len=firstDim;
            valueArray=new int[len];
            int i;
            for(i=0;i<len;i++){
                valueArray[i]==obj.valueArray[i];
            }
        }
        else if(storedType==INT_MATRIX){
            int r,c;
            r=firstDim;
            c=secondDim;
            valueMatrix = new int*[r];
            int i,j;
            for(i=0;i<r;i++){
                valueMatrix[i]=new int[c];
                for(j=0;j<c;j++){
                    valueMatrix[i][j]=obj.valueMatrix[i][j];
                }
            }
        }

    }

    void setItem (int val){
        reset();
        // write necessary code similar to that of the 2nd constructor
        value=new int;
        valueArray = NULL;
        valueMatrix = NULL;
        *value=val;
        firstDim = 0;
        secondDim = 0;
        storedType = INTEGER;
    }

    // overload setItem function so that it can dynamically allocate and initialize the member integer array
    // observe the 3rd constructor to understand its parameters and tasks

    void setItem(int *valArr, int len){

        reset();
        valueArray=new int[len];
        value = NULL;
        valueMatrix = NULL;

        int i;
        for(i=0;i<len;i++){
            valueArray[i]=valArr[i];
        }
        firstDim = len;
        secondDim = 0;
        storedType = INT_ARRAY;
    }
    // overload setItem function so that it can dynamically allocate and initialize the member integer matrix
    // observe the 4th constructor to understand its parameters and tasks
     void setItem (int **valMat, int r, int c){

        reset();
        valueMatrix = new int*[r];
        value = NULL;
        valueArray = NULL;

        int i,j;
        for(i=0;i<r;i++){
            valueMatrix[i]=new int[c];
            for(j=0;j<c;j++){
                valueMatrix[i][j]=valMat[i][j];
            }
        }

        firstDim = r;
        secondDim = c;
        storedType = INT_MATRIX;
    }



    // overload setItem function so that it can dynamically allocate and initialize the member integer matrix
    // observe the 4th constructor to understand its parameters and tasks

    // the following function returns a void* which can be cast to any pointer based on the storedType variable
    void * getItem(){
        if (value != NULL) return value;
        if (valueArray != NULL) return valueArray;
        if (valueMatrix != NULL) return valueMatrix;
        return NULL;
    }

    int getFirstDim(){
        return firstDim;
    }

    int getSecondDim(){
        return secondDim;
    }

    int getStoredType(){
        return storedType;
    }

    void print(){
        if (value != NULL){
            cout << "There is only an integer value in the container object" << endl;
            cout << "The value is: " << *value << endl;
        }
        else if (valueArray != NULL){
            cout << "There is an integer array in the container object" << endl;
            cout << "The values stored in the array are:" << endl;
            for (int i=0; i<firstDim; i++){
                cout << valueArray[i] << " ";
            }
            cout << endl;
        }
        else if (valueMatrix != NULL){
            cout << "There is an integer matrix in the container object" << endl;
            cout << "The values stored in the matrix are:" << endl;
            for (int i=0; i<firstDim; i++){
                for (int j=0; j<secondDim; j++){
                    cout << valueMatrix[i][j] << " ";
                }
                cout << endl;
            }
        }
        else{
            cout << "The object has no elements" << endl;
        }
    }

    ~Container(){
        if (value != NULL){
            cout << "Freeing allocated memory for a single integer" << endl;
            // free memory occupied by value
            delete value;
            reset();
        }
        if (valueArray != NULL){
            cout << "Freeing allocated memory for integer array" << endl;
            // free memory occupied by valueArray
            delete []valueArray;
        }
        if (valueMatrix != NULL){
            cout << "Freeing allocated memory for integer matrix" << endl;
            // free memory occupied by valueMatrix
            int i;
            for(i=0;i<firstDim;i++)
                delete[] valueMatrix[i];
            delete valueMatrix;
        }
        firstDim = 0;
        secondDim = 0;
        storedType = -1;
        cout << "_____________________" << endl;
        cout << "Destructing Container" << endl;
    }
};

class ContainerArray{
    // Do not add any additional member variable
    Container *arrayOfContainers;
    int allocatedSize;
public:
    ContainerArray(){
        allocatedSize = 0;
        arrayOfContainers = NULL;
    }

    // overload constructor so that it receives size as a parameter
    // assign size to the appropriate variable
    // allocate memory dynamically as required
    ContainerArray(int sz){
        allocatedSize = sz;
        arrayOfContainers =new Container[allocatedSize];
    }

    void setAllocatedSize(int sz){
        // if arrayOfContainers already has some entries, clear them (including freeing memory)
        // assign size (i.e. sz) to the appropriate variable
        // allocate memory dynamically as required
        delete[] arrayOfContainers;
        allocatedSize=sz;
        arrayOfContainers=new Container[allocatedSize];

    }


    int getAllocatedSize(){
        return allocatedSize;
    }

    Container getItemAt(int index){
        if (index >= allocatedSize){
            cout << "Cannot get item, Exception: Container Array index out of bound";
            exit(0);
        }
        return arrayOfContainers[index];
        // return the appropriate item from the array (i.e. arrayOfContainers);

    }

    // write a function named setItemAt, which should receive a container and an index
    // if index is out of boundary (i.e. size allocated for the array), print the following line
    // cout << "Exception: Container Array index out of bound";
    // otherwise use the setItem and getItem methods of Container class (other methods too if needed)
    // observe the main function to understand how you should type cast the items returned from getItem
    // handle all the cases that may arise for what getItem method can possibly return

    void setItemAt(Container obj,int index){
        if(index>=allocatedSize)
            cout << "Exception: Container Array index out of bound";
        else{
            if(obj.getStoredType()==INTEGER)
                arrayOfContainers[index].setItem(*((int*)obj.getItem()));
            else if(obj.getStoredType()==INT_ARRAY)
                arrayOfContainers[index].setItem((int*)obj.getItem(),obj.getFirstDim());
            else if(obj.getStoredType()==INT_MATRIX)
                arrayOfContainers[index].setItem((int**)obj.getItem(),obj.getFirstDim(),obj.getSecondDim());
        }

    }

    ~ContainerArray(){
        // free allocated memory and reset appropriate variables (i.e. set such a value)
        delete[] arrayOfContainers;
        allocatedSize=0;
    }
};

int main(){
    // the following braces indicate scope of variables declared in them
    // at the end of a brace enclosed block, object destructors will be called
    {
        Container a;
        a.print();
        if (a.getItem() == NULL){
            cout << "No value is stored in the container" << endl;
        }
        else{
            cout << "Some value is stored in the container" << endl;
        }
    }

    {
        Container a(100);
        a.print();
        if (a.getItem() == NULL){
            cout << "No value is stored in the container" << endl;
        }
        else if (a.getStoredType() == INTEGER){
            int *val = (int *) a.getItem();
            cout << "The integer value retrieved from container object is: " << *val << endl;
        }
        else{
            cout << "Some value is stored in the container" << endl;
        }
    }

    {
        int *arr = new int[3];
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;
        Container a(arr, 3);
        a.print();
        if (a.getItem() == NULL){
            cout << "No value is stored in the container" << endl;
        }
        else if (a.getStoredType() == INT_ARRAY){
            arr = (int *)a.getItem();
            cout << "The integer array retrieved from container object is: " << endl;
            for (int i=0; i<3; i++){
                cout << arr[i] << " ";
            }
            cout << endl;
        }
        else{
            cout << "Some value is stored in the container" << endl;
        }
    }

    {
        int **mat = new int*[2];
        mat[0] = new int[3];
        mat[0][0] = 1;
        mat[0][1] = 2;
        mat[0][2] = 3;
        mat[1] = new int[3];
        mat[1][0] = 4;
        mat[1][1] = 5;
        mat[1][2] = 6;
        Container a(mat, 2, 3);
        a.print();
        if (a.getItem() == NULL){
            cout << "No value is stored in the container" << endl;
        }
        else if (a.getStoredType() == INT_MATRIX){
            mat = (int **)a.getItem();
            cout << "The integer matrix retrieved from container object is: " << endl;
            for (int i=0; i<2; i++){
                for (int j=0; j<3; j++){
                    cout << mat[i][j] << " ";
                }
                cout << endl;
            }
        }
        else{
            cout << "Some value is stored in the container" << endl;
        }
    }

    Container a;
    Container b(100);

    int *arr = new int[3];
    arr[0] = 10;
    arr[1] = 20;
    arr[2] = 30;

    Container c(arr, 3);

    int **mat = new int*[2];
    mat[0] = new int[3];
    mat[0][0] = 1;
    mat[0][1] = 2;
    mat[0][2] = 3;
    mat[1] = new int[3];
    mat[1][0] = 4;
    mat[1][1] = 5;
    mat[1][2] = 6;

    Container d(mat, 2, 3);

    cout << endl;
    cout << "Demonstrating objects of ContainerArray class..." << endl;
    cout << endl;

    int firstObjArraySize = 3, secondObjArraySize = 4;
    ContainerArray containerArray1;
    ContainerArray containerArray2(secondObjArraySize);
    cout << secondObjArraySize << " constructors with empty parameters are called" << endl;

    containerArray1.setAllocatedSize(firstObjArraySize);
    cout << firstObjArraySize << " constructors with empty parameters are called" << endl;

    containerArray1.setItemAt(a, 0);
    containerArray1.setItemAt(b, 2);
    containerArray1.setItemAt(c, 1);

    containerArray2.setItemAt(c, 0);
    containerArray2.setItemAt(d, 1);

    for (int i=0; i<3; i++){
        cout << i << "-th element of 1st container array:" << endl;
        containerArray1.getItemAt(i).print();
    }

    for (int i=0; i<2; i++){
        cout << i << "-th element of 2nd container array:" << endl;
        containerArray2.getItemAt(i).print();
    }
    return 0;
}
