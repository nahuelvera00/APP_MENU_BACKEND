package com.nahuel.apirest.services;

import com.nahuel.apirest.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuServices {

    private final MenuRepository menuRepository;

    public MenuServices(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
}
