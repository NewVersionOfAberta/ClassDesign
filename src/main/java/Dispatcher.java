import exceptions.dispatcher_exceptions.StationAlreadyAddedException;
import lombok.extern.slf4j.Slf4j;
import station.Station;
import users.Passenger;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Dispatcher {
    private List<Station> stations;
    private List<Passenger> passengers;

    Dispatcher(List<Station> stations){
        this.stations = stations;
        passengers = new ArrayList<>();
        log.info("Dispatcher was created");
    }

    public Station findStation(int id){
        for (Station station : stations) {
            if (station.getId() == id) {
                return station;
            }
        }
        return null;
    }

    public void removeStation(int id){
        for (int i = 0; i < stations.size(); i++){
            if (stations.get(i).getId() == id){
                stations.remove(i);
                break;
            }
        }
    }

    public void addStation(Station station){
        if (station == null) {
            log.error("Trying to add non-existent station");
            throw new NullPointerException();
        }
        if (findStation(station.getId()) != null){
            log.error("Try to add same station #{} twice", station.getId());
            throw new StationAlreadyAddedException();
        }
        stations.add(station);
        log.info("Station #{} was added.", station.getId());
    }

    public int getStationAmount() {
        return stations == null ? 0 : stations.size();
    }
}
