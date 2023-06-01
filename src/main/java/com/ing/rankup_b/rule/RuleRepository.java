package com.ing.rankup_b.rule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RuleRepository extends JpaRepository<Rule, Integer> {

    @Query(value = "SELECT JSON_OBJECT('name',rule.name,'points',rule.points,'description',rule.description) FROM rule WHERE rule.id_rule = ?1", nativeQuery = true)
    String findRule(int idRegolaCompletata);
}
