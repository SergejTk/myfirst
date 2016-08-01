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
    // Implementation of sample interface method
    public List<WorkerDTO> getListWorkers() {
        List<Worker> list;
        List<WorkerDTO> workerDTOList = new ArrayList<>();


        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);


        WorkersService test = context.getBean(WorkersService.class);
        list = test.getAllWorkers();

        for (Worker worker : list) {
            //System.out.println(worker);
            workerDTOList.add(getWorkerDTO(worker));

        }
        return workerDTOList;
    }

    WorkerDTO getWorkerDTO(Worker worker) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.name = worker.getName();
        workerDTO.firstname = worker.getFirstname();
        workerDTO.lastname = worker.getLastname();
        workerDTO.groups = worker.getGroups();
        workerDTO.adr = worker.getAdr();
        workerDTO.kurs = worker.getKurs();
        workerDTO.ball = worker.getBall();
        workerDTO.numberinv = worker.getNumberinv();
        workerDTO.abc = worker.getAbc();
        workerDTO.def = worker.getDef();

        return workerDTO;
    }
}