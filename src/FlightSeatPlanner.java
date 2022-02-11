import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightSeatPlanner {
    private List<LinkedList<SeatDetail>> seats;

    public List<LinkedList<SeatDetail>> getSeats() {
        return seats;
    }

    public void initFlightSeats(int n, int[][] input) {
        seats = new ArrayList<>(n);

        for(int i = 0; i < input[0][1]; i++) {
            LinkedList<SeatDetail> list = new LinkedList<>();
            for(int j = 0; j < input[0][0]; j++) {
                SeatDetail seatDetail = new SeatDetail();
                seatDetail.setSeatInFlightPos(i, j);
                if(j == 0) {
                    seatDetail.setSeatType(SeatType.W);
                } else if(j == input[0][0] - 1) {
                    seatDetail.setSeatType(SeatType.A);
                } else {
                    seatDetail.setSeatType(SeatType.C);
                }

                list.add(seatDetail);
            }
            seats.add(list);
        }

        int seatColOffset = input[0][0];
        for(int inp = 1; inp < input.length - 1; inp++) {
            for(int i = 0; i < input[inp][1]; i++) {
                int seatColStartPos = input[0][0];
                for(int j = 0; j < input[inp][0]; j++) {
                    SeatDetail seatDetail = new SeatDetail();
                    seatDetail.setSeatInFlightPos(i, seatColOffset + j);
                    if(j > 0 && j < input[inp][0] - 1) {
                        seatDetail.setSeatType(SeatType.C);
                    } else {
                        seatDetail.setSeatType(SeatType.A);
                    }

                    LinkedList<SeatDetail> list;
                    try {
                        list = seats.get(i);
                    } catch (IndexOutOfBoundsException e) {
                        list = new LinkedList<>();
                        seats.add(list);
                    }
                    list.add(seatDetail);
                }
            }
            seatColOffset += input[inp][0];
        }

        for(int i = 0; i < input[input.length - 1][1]; i++) {
            for(int j = 0; j < input[input.length - 1][0]; j++) {
                SeatDetail seatDetail = new SeatDetail();
                seatDetail.setSeatInFlightPos(i, seatColOffset + j);
                if(j == 0) {
                    seatDetail.setSeatType(SeatType.A);
                } else if(j == input[input.length - 1][0] - 1) {
                    seatDetail.setSeatType(SeatType.W);
                } else {
                    seatDetail.setSeatType(SeatType.C);
                }

                LinkedList<SeatDetail> list;
                try {
                    list = seats.get(i);
                } catch (IndexOutOfBoundsException e) {
                    list = new LinkedList<>();
                    seats.add(list);
                }
                list.add(seatDetail);
            }
        }
    }

    public void printSeats() {
        for (LinkedList<SeatDetail> seatDetails: seats) {
            System.out.println();
            for (SeatDetail seatDetail : seatDetails) {
                for(int k = 0; k < seatDetail.j; k++)
                System.out.printf("%-10s", (seatDetail.getSeatType() + ":" + seatDetail.getPassNumber() + ":" + seatDetail.getSeatInFlightPos()));
            }
        }
    }


}
