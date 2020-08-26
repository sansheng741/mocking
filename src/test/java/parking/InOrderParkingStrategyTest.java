package parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.mockito.Mock;

public class InOrderParkingStrategyTest {

  @Test
  public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
     * With using Mockito to mock the input parameter */
    //given
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
    ParkingLot parkingLot = mock(ParkingLot.class);
    Car car = mock(Car.class);
    when(car.getName()).thenReturn("212");
    when(parkingLot.getName()).thenReturn("A1");
    //when
    Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
    //then
    assertNotNull(receipt.getCarName());
    assertNotNull(receipt.getParkingLotName());
  }

  @Test
  public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

    /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
     * With using Mockito to mock the input parameter */
    //given
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
    Car car = mock(Car.class);
    when(car.getName()).thenReturn("123");
    //when
    Receipt noSpaceReceipt = inOrderParkingStrategy.createNoSpaceReceipt(car);
    //then
    assertEquals("123", noSpaceReceipt.getCarName());
    assertEquals("No Parking Lot", noSpaceReceipt.getParkingLotName());
  }

  @Test
  public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
    //given
    Car car = new Car("car");
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    when(inOrderParkingStrategy.createNoSpaceReceipt(car)).thenReturn(new Receipt());
    //when
    inOrderParkingStrategy.park(null,car);
    //then
    verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
  }

  @Test
  public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
    //given
    ParkingLot parkingLot = new ParkingLot("A1",1);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    Car car = new Car("car");
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    when(inOrderParkingStrategy.createReceipt(parkingLot,car)).thenReturn(new Receipt());
    //when
    Receipt receipt = inOrderParkingStrategy.park(parkingLotList, car);
    //then
    verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot,car);
    assertNotNull(receipt);
  }

  @Test
  public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
    ParkingLot parkingLot = new ParkingLot("A1",0);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    Car car = new Car("car");
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    when(inOrderParkingStrategy.createNoSpaceReceipt(car)).thenReturn(new Receipt());
    //when
    inOrderParkingStrategy.park(parkingLotList,car);
    //then
    verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
  }

  @Test
  public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

    /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
    //given
    ParkingLot parkingLot1 = new ParkingLot("A1",0);
    ParkingLot parkingLot2 = new ParkingLot("A2",1);
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot1);
    parkingLotList.add(parkingLot2);
    Car car = new Car("car");
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    Receipt receipt = new Receipt();
    receipt.setParkingLotName("A2");
    receipt.setCarName("car");
    when(inOrderParkingStrategy.createReceipt(parkingLot2,car)).thenReturn(receipt);
    //when
    Receipt receiptResult = inOrderParkingStrategy.park(parkingLotList, car);
    //then
    verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot2,car);
    assertEquals("A2",receiptResult.getParkingLotName());
  }


}
