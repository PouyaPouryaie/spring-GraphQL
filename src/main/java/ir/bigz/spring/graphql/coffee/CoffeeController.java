package ir.bigz.spring.graphql.coffee;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @SchemaMapping(typeName = "Query", value = "findAll")
    public List<Coffee> allCoffees(){
        return coffeeService.findAll();
    }

    @SchemaMapping(typeName = "Query", value = "findOneCoffee")
    public Optional<Coffee> findOneCoffee(@Argument Integer id){
        return coffeeService.findOne(id);
    }

    @MutationMapping
    public Coffee create(@Argument String name, @Argument Size size){
        return coffeeService.create(name, size);
    }

    @MutationMapping
    public Coffee update(@Argument CoffeeDto coffeeForUpdate){
        return coffeeService.update(coffeeForUpdate.id(), coffeeForUpdate.name(), coffeeForUpdate.size());
    }

    @MutationMapping
    public Coffee delete(@Argument Integer id){
        return coffeeService.delete(id);
    }
}
