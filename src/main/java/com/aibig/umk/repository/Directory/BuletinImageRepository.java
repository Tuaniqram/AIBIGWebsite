package com.aibig.umk.repository.Directory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aibig.umk.model.Directory.BuletinFile;
import com.aibig.umk.model.Directory.BuletinImage;

public interface BuletinImageRepository extends JpaRepository<BuletinImage, Integer> {
    BuletinImage findByBuletinFileId(BuletinFile buletinFileId);

    @Query(value = "SELECT * FROM BuletinImage WHERE buletinFileId = ?1 AND buletinPage = ?2", nativeQuery = true)
    BuletinImage findByBuletinFileIdAndBuletinPage(int buletinFileId, int buletinPage);

}
