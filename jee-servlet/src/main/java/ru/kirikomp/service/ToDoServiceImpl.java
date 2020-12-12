package ru.kirikomp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kirikomp.persist.ToDo;
import ru.kirikomp.persist.ToDoCategory;
import ru.kirikomp.persist.ToDoCategoryRepository;
import ru.kirikomp.persist.ToDoRepository;
import ru.kirikomp.rest.ToDoServiceRs;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
@WebService(endpointInterface = "ru.kirikomp.service.ToDoServiceWs", serviceName = "ToDoService")
public class ToDoServiceImpl implements ToDoServiceLocal, ToDoServiceRemote, ToDoServiceWs, ToDoServiceRs {

    private static final Logger logger = LoggerFactory.getLogger(ToDoServiceImpl.class);

    @Inject
    private ToDoRepository toDoRepository;

    @Inject
    private ToDoCategoryRepository toDoCategoryRepository;

    @TransactionAttribute
    @Override
    public void insert(ToDoRepr toDoRepr) {
        ToDoCategory category = toDoCategoryRepository.findById(toDoRepr.getCategoryId());
        toDoRepository.insert(new ToDo(null, toDoRepr.getDescription(), toDoRepr.getTargetDate(), category));
    }

    @TransactionAttribute
    @Override
    public void update(ToDoRepr toDoRepr) {
        ToDoCategory category = toDoCategoryRepository.findById(toDoRepr.getCategoryId());
        toDoRepository.update(new ToDo(toDoRepr.getId(), toDoRepr.getDescription(), toDoRepr.getTargetDate(), category));
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        toDoRepository.delete(id);
    }

    @Override
    public ToDoRepr findById(long id) {
        return toDoRepository.findToDoReprById(id);
    }

    @Override
    public List<ToDoRepr> findAll() {
        return toDoRepository.findAllToDoRepr();
    }

    @Asynchronous
    @Override
    public Future<ToDoRepr> findByIdAsync(long id) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(toDoRepository.findToDoReprById(id));
    }
}
