package com.expidev.gcmapp.utils;

import android.content.Intent;
import android.content.IntentFilter;

import com.expidev.gcmapp.service.AssociatedMinistriesService;
import com.expidev.gcmapp.service.AuthService;
import com.expidev.gcmapp.service.TrainingService;
import com.expidev.gcmapp.service.Type;

/**
 * Created by matthewfrederick on 1/23/15.
 */
public final class BroadcastUtils
{
    public static final String ACTION_START = BroadcastUtils.class.getName() + ".ACTION_START";
    public static final String ACTION_RUNNING = BroadcastUtils.class.getName() + ".ACTION_RUNNING";
    public static final String ACTION_STOP = BroadcastUtils.class.getName() + ".ACTION_STOP";
    
    public static final String ACTION_TYPE = BroadcastUtils.class.getName() + ".ACTION_TYPE";

    public static final String TICKET_RECEIVED = AuthService.class.getName() + ".TICKET_RECEIVED";
    public static final String TRAINING_RECEIVED = TrainingService.class.getName() + ".TRAINING_RECEIVED";
    public static final String ACTION_RETRIEVE_ASSOCIATED_MINISTRIES =
        AssociatedMinistriesService.class.getName() + ".ACTION_RETRIEVE_ASSOCIATED_MINISTRIES";
    public static final String ACTION_RETRIEVE_ALL_MINISTRIES =
        AssociatedMinistriesService.class.getName() + ".ACTION_RETRIEVE_ALL_MINISTRIES";
    public static final String ACTION_SAVE_ALL_MINISTRIES =
        AssociatedMinistriesService.class.getName() + ".ACTION_SAVE_ALL_MINISTRIES";
    public static final String ACTION_LOAD_ALL_MINISTRIES =
        AssociatedMinistriesService.class.getName() + ".ACTION_LOAD_ALL_MINISTRIES";

    public static Intent startBroadcast()
    {
        return new Intent(ACTION_START);
    }

    public static Intent runningBroadcast()
    {
        return new Intent(ACTION_RUNNING);
    }

    public static Intent stopBroadcast(Type type)
    {
        Intent intent = new Intent(ACTION_STOP);
        intent.putExtra(ACTION_TYPE, type);
        return intent;
    }

    public static IntentFilter startFilter()
    {
        return new IntentFilter(ACTION_START);
    }

    public static IntentFilter runningFilter()
    {
        return new IntentFilter(ACTION_RUNNING);
    }

    public static IntentFilter stopFilter()
    {
        return new IntentFilter(ACTION_STOP);
    }

    public static Intent ticketReceivedBroadcast(String ticket)
    {
        Intent intent = new Intent(ACTION_STOP);
        intent.putExtra("ticket", ticket);
        return intent;
    }
    
    public static Intent trainingReceivedBroadcast()
    {
        return new Intent(TRAINING_RECEIVED);
    }
}
