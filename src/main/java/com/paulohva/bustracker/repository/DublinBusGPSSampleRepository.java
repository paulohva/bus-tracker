package com.paulohva.bustracker.repository;

import com.paulohva.bustracker.domain.DublinBusGPSSample;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DublinBusGPSSampleRepository extends MongoRepository<DublinBusGPSSample, String> {

    List<DublinBusGPSSample> findByOperator(String text);

    List<DublinBusGPSSample> findByTimestampBetween(long timestampGT, long timestampLT);
}