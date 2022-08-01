package whale.whale_Project;

import lombok.Getter;
import org.hibernate.engine.jdbc.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import whale.whale_Project.domain.MbtiMappingWithWhale;
import whale.whale_Project.domain.Whale;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class WhaleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhaleProjectApplication.class, args);

	}
}
