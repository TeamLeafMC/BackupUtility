package net.mov51.failsafe;

public class notify {
    //todo handle potential failures at every step of the process and notify the user through multiple means

    //todo pterodactyl connection failure
    // what command couldn't be sent
    // what step of the backup process was the system at
    // how many attempts were made
    // what error code was received

    //todo archive failure
    // attempt to sort out possible issues
    // what step was the archive on, zip, upload, or other
    // don't attempt again

    //todo initialization failsafe
    // if the program fails at loading either the core of global configuration files then there probably
    // won't be any way of notifying anyone of a failure. But I should be able to log the failure and attempt to read
    // the backup configs as is with the yaml verifier and log any missing data. That should allow users to have a
    // very strict configuration policy by having their API keys and panel information defined in multiple locations,
    // but it would be a pain. In theory there's no reason why it should fail if it worked before, but I can't count
    // on it for certain.

    //todo rsync failsafe
    // try again once
    // assume that the connection is closed
    // attempt to send the finish commands
    // hold the result of those
    //  if they failed, don't send failure notifications.
    // notify the user that the backup failed
    // notify the user of the result of the finish commands
}
