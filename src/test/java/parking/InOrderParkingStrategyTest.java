package parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

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
    assertEquals("No Parking Lot",noSpaceReceipt.getParkingLotName());
  }

  @Test
  public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

  }

  @Test
  public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

  }

  @Test
  public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

  }

  @Test
  public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

    /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

  }


}
