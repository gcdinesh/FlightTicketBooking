public class FlightTicketBooking {

    public static void main(String[] args) {
        int[][] input = new int[3][2];
//        input[0] = new int[]{3, 2};
//        input[1] = new int[]{4, 3};
//        input[2] = new int[]{2, 3};
//        input[3] = new int[]{3, 4};
        input[0] = new int[]{5, 4};
        input[1] = new int[]{1, 1};
        input[2] = new int[]{7, 11};
        int noOfWaitingPassenger = 30;


        int numberOfAisleSeat = 0;
        int numberOfLinkedList = 0;
        int numberOfWindowSeat = 0;

        for(int i = 0; i < input.length; i++) {
            numberOfLinkedList = Math.max(numberOfLinkedList, input[i][1]);
            if(i == 0 || i == input.length - 1) {
                    numberOfAisleSeat += input[i][1];
                    if(input[i][0] != 1) { // if the number of seats is just one then consider it for aisle seat
                        numberOfWindowSeat += input[i][1];
                    }
                } else {
                    numberOfAisleSeat += input[i][1] * 2;
                }
        }

        int aisleStartPos = 1;
        int windowStartPos = numberOfAisleSeat + 1;
        int centerStartPos = windowStartPos + numberOfWindowSeat;

        System.out.println("aislePos:" + aisleStartPos + " windowPos:" + windowStartPos + " centerPos:" + centerStartPos + "\n");

        FlightSeatPlanner flightSeatPlanner = new FlightSeatPlanner();
        flightSeatPlanner.initFlightSeats(numberOfLinkedList, input);


        PassengerSeatAssigner passengerSeatAssigner = new PassengerSeatAssigner();
        passengerSeatAssigner.assignSeats(flightSeatPlanner.getSeats(), aisleStartPos, windowStartPos, centerStartPos, noOfWaitingPassenger);

        flightSeatPlanner.printSeats();
    }
}
