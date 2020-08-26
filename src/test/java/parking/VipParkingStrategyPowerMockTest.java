package parking;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})
public class VipParkingStrategyPowerMockTest {

  @Test
  public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice() {

    /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
     * by using PowerMock to mock static method */
    //given
    mockStatic(ParkingLot.class);
    //when
    when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
    VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
    Integer price = vipParkingStrategy.calculateHourlyPrice();
    //then
    assertEquals(50L, price.longValue());
  }

  @Test
  public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice() {

    /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
     * by using PowerMock to mock static method */
    //given
    mockStatic(ParkingLot.class);
    //when
    when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);
    VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
    Integer price = vipParkingStrategy.calculateHourlyPrice();
    //then
    assertEquals(40L, price.longValue());
  }
}
