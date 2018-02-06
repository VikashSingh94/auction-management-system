package com.hashmap.service;


import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.dao.InMemoryDoa;

import java.util.UUID;

interface Listener
{
    void onEvent();
}

class AuctionEndListener implements Listener {

    UUID auctionId;

    public AuctionEndListener(UUID auctionId)
    {
        this.auctionId = auctionId;
    }

    @Override
    public void onEvent()
    {
        InMemoryDoa dataAccessLayer = new InMemoryDAOImpl();
        dataAccessLayer.updateIsAuctionOpen(this.auctionId, false);
    }
}


