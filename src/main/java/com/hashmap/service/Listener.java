package com.hashmap.service;


import com.hashmap.dao.InMemoryDAOImpl;
import com.hashmap.dao.InMemoryDoa;

import java.util.UUID;

interface Listener
{
    void onAuctionEnd();
}

class AuctionListener implements Listener {

    UUID auctionId;

    public AuctionListener(UUID auctionId)
    {
        this.auctionId = auctionId;
    }

    @Override
    public void onAuctionEnd()
    {
        InMemoryDoa dataAccessLayer = new InMemoryDAOImpl();
        dataAccessLayer.updateIsAuctionOpen(this.auctionId, false);
    }
}


