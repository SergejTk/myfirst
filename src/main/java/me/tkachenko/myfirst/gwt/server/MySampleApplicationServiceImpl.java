package me.tkachenko.myfirst.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import me.tkachenko.myfirst.config.BeanConfig;
import me.tkachenko.myfirst.gwt.client.MySampleApplicationService;
import me.tkachenko.myfirst.gwt.shared.WorkerDTO;
import me.tkachenko.myfirst.model.Worker;
import me.tkachenko.myfirst.service.WorkersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {


    private ApplicationContext context =
            new AnnotationConfigApplicationContext(BeanConfig.class);

    private WorkersService test = context.getBean(WorkersService.class);

    // Implementation of sample interface method
    public List<WorkerDTO> getListWorkers() {
        List<Worker> list;
        List<WorkerDTO> workerDTOList = new ArrayList<>();


        list = test.getAllWorkers();

        for (Worker worker : list) {

            workerDTOList.add(getWorkerDTO(worker));

        }
        return workerDTOList;
    }

    // Implementation of sample interface method
    public List<WorkerDTO> getPartWorkers(int start, int length, String columnName, boolean isAsc) {

        List<Worker> list;
        List<WorkerDTO> workerDTOList = new ArrayList<>();

        list = test.getPartWorkers(start, length, columnName, isAsc);

        for (Worker worker : list) {

            workerDTOList.add(getWorkerDTO(worker));

        }


        return workerDTOList;
    }

    // Implementation of sample interface method
    public Number getTotalRow() {
        return test.getTotalRow();
    }

    private WorkerDTO getWorkerDTO(Worker worker) {

        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setName(worker.getName());
        workerDTO.setFirstname(worker.getFirstname());
        workerDTO.setLastname(worker.getLastname());
        workerDTO.setGroups(worker.getGroups());
        workerDTO.setAdr(worker.getAdr());
        workerDTO.setKurs(worker.getKurs());
        workerDTO.setBall(worker.getBall());
        workerDTO.setNumberinv(worker.getNumberinv());
        workerDTO.setAbc(worker.getAbc());
        workerDTO.setDef(worker.getDef());

        return workerDTO;
    }


    public void updateWorker(WorkerDTO workerDTO) {
        Worker worker = getWorker(workerDTO);

        test.updateWorker(worker);

    }


    public void deleteWorker(WorkerDTO workerDTO) {
        Worker worker = getWorker(workerDTO);
        test.deleteWorker(worker);
    }

    private Worker getWorker(WorkerDTO workerDTO) {
        Worker worker = new Worker();
        worker.setName(workerDTO.getName());
        worker.setFirstname(workerDTO.getFirstname());
        worker.setLastname(workerDTO.getLastname());
        worker.setGroups(workerDTO.getGroups());
        worker.setAdr(workerDTO.getAdr());
        worker.setKurs(workerDTO.getKurs());
        worker.setBall(workerDTO.getBall());
        worker.setNumberinv(workerDTO.getNumberinv());
        worker.setAbc(workerDTO.getAbc());
        worker.setDef(workerDTO.getDef());
        return worker;

    }
}