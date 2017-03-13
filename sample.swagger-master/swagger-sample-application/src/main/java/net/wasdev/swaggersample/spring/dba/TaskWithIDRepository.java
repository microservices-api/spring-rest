package net.wasdev.swaggersample.spring.dba;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//------------------------------------------------------------------------------------------------
// JpaRepository makes SQL-FREE. @Query makes SQL flexible.
//------------------------------------------------------------------------------------------------
public interface TaskWithIDRepository extends JpaRepository<TableDefinition, Integer>{

	@Query("SELECT MAX(id) FROM TableDefinition")
	public Integer findMaxId();

	// = "SELECT * FROM TableDefinition ORDER BY id"
	public List<TableDefinition> findAllByOrderByIdAsc();

	// = "SELECT * FROM TableDefinition t WHERE t.id = :cID"
	public TableDefinition findByid(Integer id);


	// You shouldn't use Entity name (TableDefinition), but real table name (task_with_id)
	// * It's limited only for INSERT @Query
	@Modifying
	@Query(value = "INSERT INTO task_with_id (id, description, status, deadline)"
			+ "VALUES (:cID, :cDescription, :cStatus, :cDeadline)", nativeQuery = true)
	public Integer create(@Param ("cID") Integer id,
			@Param ("cDescription") String description, 
			@Param ("cStatus") String status,
			@Param ("cDeadline") String deadline);


	@Modifying
	@Query("UPDATE TableDefinition t "
			+ "SET t.description =:cDescription, t.status =:cStatus, t.deadline=:cDeadline "
			+ "WHERE t.id =:cID)")
	public Integer save(@Param ("cID") Integer id,
			@Param ("cDescription") String description, 
			@Param ("cStatus") String status,
			@Param ("cDeadline") String deadline);


	@Override
	@Modifying
	@Query("DELETE FROM TableDefinition t "
			+ "WHERE t.id =:cID)")
	public void delete(@Param ("cID") Integer id);
}
