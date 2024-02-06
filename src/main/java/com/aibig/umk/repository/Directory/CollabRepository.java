package com.aibig.umk.repository.Directory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aibig.umk.model.Directory.Collaborations;

public interface CollabRepository extends JpaRepository<Collaborations, Integer> {

    Collaborations findByCollaborationName(String collaborationName);
}