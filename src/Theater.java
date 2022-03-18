import java.util.*;

public class Theater {
    int rows;
    int columns;
    int numberOfSeatsLeft;
    int totalCustomer;
    int invalidGroup;
    int[] remainingSeatsEachRow;
    int[] rowPointer;
    String[][] seats;

    LinkedHashMap<String, List<String>> seatingAssignment;

    public Theater(){
        rows = 10;
        columns = 20;
        numberOfSeatsLeft = rows * columns;
        totalCustomer = 0;
        invalidGroup = 0;
        remainingSeatsEachRow = new int[10];
        Arrays.fill(remainingSeatsEachRow, 20);
        seats = new String[10][20];
        rowPointer = new int[10];
        Arrays.fill(rowPointer, 0);
        seatingAssignment = new LinkedHashMap<>();
    }


    public int reserve(String reservationRequest){
        int output;
        String[] input = reservationRequest.split(" ");
        String reservationNumber = input[0];
        int numberOfTickets = Integer.parseInt(input[1]);
        int group = numberOfTickets;
        if(numberOfTickets > 0){
            if(numberOfSeatsLeft >= numberOfTickets){
                output = assignSeats(reservationNumber, group);
                return output;


            } else{ // not enough space for this reservation request
                return -1;
            }
        } else{ // invalid number of ticket booked
            invalidGroup++;
            String[] strArray = reservationRequest.split(" ");
            String str = strArray[0];
            List<String> list = new ArrayList<>();
            list.add("Invalid number of ticket booked.");
            seatingAssignment.put(str, list);
            return 1;
        }
    }



    private int assignSeats(String reservationNumber, int seatsToBook) {
//        if(seatsToBook > numberOfSeatsLeft) return 1; // not enough space for this group;

        int count = 1;
        boolean check = true;
        int r = (rows / 2) - 1;

        //if we can put a group into the same row (first priority)
        while(r >= 0 && r < rows){
            // if we find a row that can fit the entire group of people, we assign seats for this group of people
            if(remainingSeatsEachRow[r] >= seatsToBook){
                for(int c= rowPointer[r]; c < rowPointer[r] + seatsToBook; c++){
                    seats[r][c] = reservationNumber;
                    if(seatingAssignment.containsKey(reservationNumber)){
                        seatingAssignment.get(reservationNumber).add((char)(r + 65) + Integer.toString(c + 1));
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add((char)(r + 65) + Integer.toString(c + 1));
                        seatingAssignment.put(reservationNumber, list);
                    }
                }
                totalCustomer += seatsToBook;
                remainingSeatsEachRow[r] = remainingSeatsEachRow[r] - seatsToBook - 3 >= 0 ? remainingSeatsEachRow[r] - seatsToBook - 3 : 0;
                numberOfSeatsLeft = numberOfSeatsLeft - seatsToBook - 3 >= 0 ? numberOfSeatsLeft - seatsToBook - 3 : 0;
                rowPointer[r] = rowPointer[r] + seatsToBook + 3 <= 19 ? rowPointer[r] + seatsToBook + 3 : 19;
                seatsToBook = 0;
                if(seatsToBook == 0) return 0;
            }

            if (check) {
                r = r + count;
                count++;
                check = false;
            } else {
                r = r - count;
                count++;
                check = true;
            }
        }



        //then we will try to split the group and scatter them into available spots
        if (seatsToBook > 0) {
            count = 1;
            check = true;
            int i = (rows / 2) - 1;
            while (i >= 0 && i < rows){
                if(remainingSeatsEachRow[i] > 0){
                    for(int j = rowPointer[i]; j < 20; j++){
                        seats[i][j] = reservationNumber;
                        if(seatingAssignment.containsKey(reservationNumber)){
                            seatingAssignment.get(reservationNumber).add((char)(i + 65) + Integer.toString(j + 1));
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add((char)(i + 65) + Integer.toString(j + 1));
                            seatingAssignment.put(reservationNumber, list);
                        }

                        seatsToBook--;
                        totalCustomer++;

                        //check if this group has all been assigned seats
                        if(seatsToBook == 0){
                            remainingSeatsEachRow[i] = remainingSeatsEachRow[i] - 4 >= 0 ? remainingSeatsEachRow[i] - 4 : 0;
                            numberOfSeatsLeft = numberOfSeatsLeft - 4 >= 0 ? numberOfSeatsLeft - 4 : 0;
                            rowPointer[i] = rowPointer[i] + 4 <= 19 ? rowPointer[i] + 4 : 19;
                            return 0;
                        } else {
                            remainingSeatsEachRow[i]--;
                            numberOfSeatsLeft--;
                            rowPointer[i]++;
                        }

                    }
                }

                if (check) {
                    i = i + count;
                    count++;
                    check = false;
                } else {
                    i = i - count;
                    count++;
                    check = true;
                }
            }
        }
        return 0;
    }

    public List<String> getList(int row, int columnStart, int columnEnd){
        List<String> list = new ArrayList<>();
        for(int c = columnStart; c <= columnEnd; c++){
            list.add(seats[row][c]);
        }
        return  list;
    }

    public Map<String, List<String>> getResult(){
        return this.seatingAssignment;
    }

    public int getNumberOfSeatsLeft(){
        return this.numberOfSeatsLeft;
    }

    public void report(){
        System.out.println("********Report********");
        System.out.println("Total number of valid group is:" + (seatingAssignment.size() - invalidGroup));
        System.out.println("Total number of audience: " + totalCustomer);
    }

    public void printLayout(){
        System.out.println("*********************Reservation*********************");
        for(int r = 0; r < 10; r++){
            System.out.print((char)(r + 65) + " ");
            for(int c = 0; c < 20; c++){
                System.out.print(" " + seats[r][c]);
            }
            System.out.println();
        }
    }


}






