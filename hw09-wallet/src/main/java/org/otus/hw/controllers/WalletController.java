package org.otus.hw.controllers;

import org.otus.hw.entities.Wallet;
import org.otus.hw.repo.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {
    @Autowired
    private WalletRepo walletRepo;

    @GetMapping("/{userID}")
    public ResponseEntity<Wallet> read(@PathVariable Integer userID) {
        Wallet w = walletRepo.read(userID);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    @GetMapping("/{userID}/topUp")
    public ResponseEntity<Wallet> topUp(@PathVariable Integer userID, @RequestParam Integer amount) {
        Wallet w = walletRepo.topUp(userID, amount);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    @GetMapping("/{userID}/withdraw")
    public ResponseEntity<Map<String, String>> withdraw(@PathVariable Integer userID, @RequestParam Integer amount) {
        boolean res = walletRepo.withdraw(userID, amount);
        Map<String, String> resp = Collections.singletonMap("res", res ? "ok" : "fail");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
