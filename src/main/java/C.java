public class C extends B{
    public void display(){
        System.out.println("Class C");
    }
    public static void main(String[] args) {
        B obj = new B();
        obj.display();
    }

}
