package EventManager.emanuele.services;

import EventManager.emanuele.entities.Event;
import EventManager.emanuele.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class EventService {

    List<Event> eventList = new ArrayList<>();

    public Event save(Event body){
        this.eventList.add(body);
        return body;
    };

    public List<Event> getEventList() {
        return eventList;
    }

    public Event findById (int id) {
        Event e = null;
        for (Event event: this.eventList) {
            if (event.getId() == id) {
                e = event;
            }
        }
        if(e == null ){
            throw new NotFoundException(id);
        } else {
            return e;
        }
    }

    public void findByIdAndDelete(int id) {
        ListIterator<Event> iterator = this.eventList.listIterator();
        while (iterator.hasNext()) {
            Event selectedEvent = iterator.next();
            if (selectedEvent.getId() == id) {
                iterator.remove();
            }
        }
    }

    public Event findByIdAndUpdate(int id, Event body){
        Event selectedEvent = null;

        for (Event event: this.eventList) {
            if (event.getId() == id) {
                selectedEvent = event;
                selectedEvent.setId(id);
                selectedEvent.setEventDay(body.getEventDay());
                selectedEvent.setTitle(body.getTitle());
                selectedEvent.setLocation(body.getLocation());
                selectedEvent.setDescription(body.getDescription());
                selectedEvent.setAudienceCapacity(body.getAudienceCapacity());
            }
        }
        if(selectedEvent == null ){
            throw new NotFoundException(id);
        } else {
            return selectedEvent;
        }
    }


}
