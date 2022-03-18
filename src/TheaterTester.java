import java.util.ArrayList;
import java.util.List;

public class TheaterTester {
    Theater theaterTester;

    TheaterTester(){}

    public void test(Theater theaterTester){
        System.out.println("********** Test start **********");
        this.theaterTester = theaterTester;


        reservationWithAmountZeroTest();
        insufficientSeatsTest();
        threeSeatsBufferTest();
        consecutiveSeatingOfAGroupTest();
        reservationWith20Tickets();
    }

    private void reservationWithAmountZeroTest(){
        if(theaterTester.reserve("R001 0") == 1){
            System.out.println("Test1 Passed: No seats reserved with Reservation R001");
        } else {
            System.out.println("Test1 Failed: Reservation R001 has no seat reserved");
        }
    }

    private void insufficientSeatsTest(){
        theaterTester.reserve("R010 300");
        if(theaterTester.getNumberOfSeatsLeft() > 0){
            System.out.println("Test2 Passed: Failed to complete R010's reservation with 300 tickets, which is greater than available seats");
        } else {
            System.out.println("Test2 Failed: Should not complete this order with ordered ticket amount bigger than available seats");
        }
    }

    private void threeSeatsBufferTest(){
        theaterTester.reserve("R003 1");
        theaterTester.reserve("R004 1");

        List<String> list = new ArrayList<>();
        list.add("R003");
        list.add(null);
        list.add(null);
        list.add(null);
        list.add("R004");

        if(theaterTester.getList(4, 0, 4).equals(list)){
            System.out.println("Test3 Passed: Different group of reservation will be separated by a seating of three buffer seats");
        } else {
            System.out.println("Test3 Failed: Different group of reservation should be separated by a seating of three buffer seats");
        }

    }

    private void consecutiveSeatingOfAGroupTest(){
        theaterTester.reserve("R005 3");
        List<String> list = new ArrayList<>();
        list.add("R005");
        list.add("R005");
        list.add("R005");

        if(theaterTester.getList(4, 8, 10).equals(list)){
            System.out.println("Test4 Passed: A reservation of a group that can be assigned within the same row is assigned next to each other");
        } else {
            System.out.println("Test4 Failed: A reservation of a group that should be assigned within the same row is assigned next to each other");
        }
    }


    private void reservationWith20Tickets(){
        theaterTester.reserve("R006 20");
        List<String> list = new ArrayList<>();
        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");

        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");

        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");

        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");
        list.add("R006");

        if(theaterTester.getList(5,0,19).equals(list)){
            System.out.println("Test5 Passed: A reservation of a group is assigned to a single row when available");
        } else {
            System.out.println("Test5 Failed: A reservation of a group is assigned to a single row when available");
        }
    }



}
