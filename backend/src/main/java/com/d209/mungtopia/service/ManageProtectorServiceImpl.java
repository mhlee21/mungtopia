package com.d209.mungtopia.service;

import com.d209.mungtopia.repository.ManageProtectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// BL
@Service
@RequiredArgsConstructor
public class ManageProtectorServiceImpl implements ManageProtectorService{

    private final ManageProtectorRepository manageProtectorRepository;

}
