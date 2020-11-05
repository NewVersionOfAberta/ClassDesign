import org.junit.jupiter.api.Test;
import station.Station;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DispatcherTest {

    private List<Station> buildStations(int amount){
        List<Station> stations = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            stations.add(new Station(i));
        }
        return stations;
    }

    @Test
    void findStation_create3_find1() {
        int stationId = 1;
        int stationAmount = 3;
        Station expectedStation;
        Station actualStation;

        List<Station> stations = buildStations(stationAmount);
        Dispatcher dispatcher = new Dispatcher(stations);

        expectedStation = stations.get(stationId);
        actualStation = dispatcher.findStation(stationId);

        assertEquals(dispatcher.getStationAmount(), stationAmount);
        assertEquals(expectedStation, actualStation);

    }

    @Test
    void addStation_init3add1_4() {
        int expected = 4;
        int stationAmount = 3;
        int actual;

        List<Station> stations = buildStations(stationAmount);
        Dispatcher dispatcher = new Dispatcher(stations);

        dispatcher.addStation(new Station(stationAmount));
        actual = dispatcher.getStationAmount();
        assertEquals(expected, actual);
    }

    @Test
    void removeStation_init4remove1_3() {
        int expected = 3;
        int idToRemove = 1;
        int stationAmount = 4;
        int actual;

        List<Station> stations = buildStations(stationAmount);
        Dispatcher dispatcher = new Dispatcher(stations);

        dispatcher.removeStation(idToRemove);
        actual = dispatcher.getStationAmount();
        assertEquals(expected, actual);

    }
}