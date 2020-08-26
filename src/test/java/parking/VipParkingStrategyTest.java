package parking;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
      //given
      Car car = new Car("A1");
      ParkingLot parkingLot = new ParkingLot("park1",0);
      List<ParkingLot> parkingLotList = new ArrayList<>();
      parkingLotList.add(parkingLot);
      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
      when(vipParkingStrategy.isAllowOverPark(car)).thenReturn(true);
      when(vipParkingStrategy.createReceipt(parkingLot,car)).thenReturn(new Receipt());
      //when
      Receipt receipt = vipParkingStrategy.park(parkingLotList, car);
      //then
      verify(vipParkingStrategy,times(1)).createReceipt(parkingLot,car);
      assertNotNull(receipt);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        Car car = new Car("A1");
        ParkingLot parkingLot = new ParkingLot("park1",0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        when(vipParkingStrategy.isAllowOverPark(car)).thenReturn(false);
        when(vipParkingStrategy.createNoSpaceReceipt(car)).thenReturn(null);
        //when
        Receipt receipt = vipParkingStrategy.park(parkingLotList, car);
        //then
        verify(vipParkingStrategy,times(1)).createNoSpaceReceipt(car);
        assertNull(receipt);
    }

    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

	  @Mock
    CarDaoImpl carDao;

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = new ParkingLot("park1",1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        String carName = "A2";
        Car car = new Car(carName);
        when(carDao.isVip(carName)).thenReturn(true);
        //when
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertTrue(allowOverPark);

    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = new ParkingLot("park1",1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        String carName = "B2";
        Car car = new Car(carName);
        when(carDao.isVip(carName)).thenReturn(true);
        //when
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = new ParkingLot("park1",1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        String carName = "A2";
        Car car = new Car(carName);
        when(carDao.isVip(carName)).thenReturn(false);
        //when
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = new ParkingLot("park1",1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        String carName = "B2";
        Car car = new Car(carName);
        when(carDao.isVip(carName)).thenReturn(false);
        //when
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(allowOverPark);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
