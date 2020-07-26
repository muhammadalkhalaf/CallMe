# CallMe  

## Detect Incoming Call  

This project detects incoming call and once incoming call is recieved it shows popup message but unfortunately it can't get phone number.  

It uses `BroadcastReceiver` for detect phone state and requires `READ_PHONE_STATE` permission, 
When call is recieved `onReceive` method is called with `android.intent.action.PHONE_STATE` action 
then it uses `TelephonyManager` class for set listener on call state and when state becomes `CALL_STATE_RINGING`
that means call is recieved and the phone is ringing,  
at that moment it opens Activity as popup alert using `Theme.AppCompat.Dialog.Alert` theme and `singleInstance` launchMode for launch independent of others activities.
  
