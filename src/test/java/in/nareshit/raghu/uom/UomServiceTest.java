package in.nareshit.raghu.uom;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.nareshit.raghu.exception.UomNotFoundException;
import in.nareshit.raghu.model.Uom;
import in.nareshit.raghu.repo.UomRepository;
import in.nareshit.raghu.service.IUomService;
import in.nareshit.raghu.service.impl.UomServiceImpl;
import io.swagger.annotations.Authorization;
import net.bytebuddy.asm.Advice.Argument;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
public class UomServiceTest {
	
	@Mock(lenient = true)
	UomRepository repository;
	
	@InjectMocks
	UomServiceImpl service;
	
//	@Autowired
//	Environment env;
	
	
	@Test
	public void saveUomTest() {
		Uom uom = new Uom();
		uom.setUomModel("Sa");
		OngoingStubbing<Uom> when2 = when(repository.save(ArgumentMatchers.any(Uom.class)));
		when2.thenReturn(new Uom(1, "Uom", "test", "cotimr"));
		Integer saveUom = service.saveUom(uom);
		System.out.println(saveUom);
		assertNotNull(saveUom);
	}

	@Test
	public void updateUomWithSaveTest() {
		Uom uom = new Uom();
		uom.setId(1);
		when(repository.existsById(anyInt())).thenReturn(true);
		service.updateUom(uom);
		verify(repository,times(1)).save(uom);		
	}
	
	@Test
	public void updateUomwithExceptionTest() {
		Uom uom = new Uom();
		uom.setId(null);
		when(repository.existsById(anyInt())).thenReturn(false);
		
		verify(repository,never()).save(uom);	
		assertThrows(UomNotFoundException.class, ()->{
			service.updateUom(uom);
		});
	}
	
	
	

	
	
}
