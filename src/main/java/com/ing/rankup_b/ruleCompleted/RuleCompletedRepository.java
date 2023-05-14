package com.ing.rankup_b.ruleCompleted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RuleCompletedRepository extends JpaRepository<RuleCompleted, Integer> {

    @Query(value = "SELECT JSON_OBJECT('username',user.username,'name',rule.name,'timestamp',rule_completed.timestamp,'attached',rule_completed.attached) FROM rule_completed JOIN rule ON rule_completed.id_rule = rule.id_rule JOIN user ON user.id_user = rule_completed.id_user WHERE rule_completed.id_rule_completed = ?1", nativeQuery = true)
    String findRule(int idRegolaCompletata);
}
