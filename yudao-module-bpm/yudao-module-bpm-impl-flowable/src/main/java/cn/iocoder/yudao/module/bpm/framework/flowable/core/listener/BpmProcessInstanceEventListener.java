package cn.iocoder.yudao.module.bpm.framework.flowable.core.listener;

import cn.iocoder.yudao.module.bpm.dal.dataobject.task.BpmProcessInstanceExtDO;
import cn.iocoder.yudao.module.bpm.service.task.BpmProcessInstanceService;
import com.google.common.collect.ImmutableSet;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEntityEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.engine.delegate.event.AbstractFlowableEngineEventListener;
import org.flowable.engine.delegate.event.FlowableCancelledEvent;
import org.flowable.engine.delegate.event.FlowableProcessStartedEvent;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
/**
 * 监听 {@link ProcessInstance} 的开始与完成，创建与更新对应的 {@link BpmProcessInstanceExtDO} 记录
 *
 * @author jason
 */
@Component
public class BpmProcessInstanceEventListener extends AbstractFlowableEngineEventListener {

    @Resource
    @Lazy
    private BpmProcessInstanceService processInstanceService;

    public static final Set<FlowableEngineEventType> PROCESS_INSTANCE_EVENTS = ImmutableSet.<FlowableEngineEventType>builder()
                     .add(FlowableEngineEventType.PROCESS_CREATED)
                     .add(FlowableEngineEventType.PROCESS_STARTED)
                     .add(FlowableEngineEventType.PROCESS_CANCELLED)
                     .build();

    public BpmProcessInstanceEventListener(){
        super(PROCESS_INSTANCE_EVENTS);
    }

    @Override
    protected void processCreated(FlowableEngineEntityEvent event) {
        processInstanceService.createProcessInstanceExt((ProcessInstance)event.getEntity());
    }

    @Override
    protected void processStarted(FlowableProcessStartedEvent event) {
        super.processStarted(event);
    }


    @Override
    protected void processCancelled(FlowableCancelledEvent event) {
        super.processCancelled(event);
    }
}