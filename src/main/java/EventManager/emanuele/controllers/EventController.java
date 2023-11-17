package EventManager.emanuele.controllers;

import EventManager.emanuele.entities.Event;
import EventManager.emanuele.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("")
    public List<Event> eventList() {
        return eventService.getEventList();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody Event body) {
        return eventService.save(body);
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable int id) {
        return eventService.findById(id);
    }

    @PutMapping("/{id}")
    public Event findByIdAndUpdate(@PathVariable int id, @RequestBody Event body) {
        return eventService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        eventService.findByIdAndDelete(id);
    }
    
}
