package EventManager.emanuele.services;

import EventManager.emanuele.entities.Event;
import EventManager.emanuele.exceptions.NotFoundException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class EventService {

    @Autowired
    private Cloudinary cloudinary;
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

    public static String uploadPicture(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }


}
