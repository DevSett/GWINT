package app.classes.webNetwork.config;

public enum CommandGwent {
    CONNECTION("Connection"),
    DISCONNECTION("Disconnection"),
    CREATE_LOBBI("CreateLobbi"),
    REMOVE_LOBBI("RemoveLobbi"),
    CONNECTED_LOBBI("ConnectedLobbi"),
    DISCONNECTED_LOBBI("DisconnectedLobbi"),
    READYTOLOBBI("ReadyLobbi"),
    IDCARDSHAND("IdCardsHand"),
    IDCARDSPACK("IdCardsPack"),
    IDCARDTRASH("IdCardsTrash"),
    DAMAGE("Damage"),
    EFFECT("Effect"),
    START_GAME("StartGame"),
    STEP("Step"),
    NICKNAME("Nickname"),
    NEW_CONNECTED("NewConnected"),
    INFO_USERS("InfoUsers"),
    DISCONNECTED("Disconnected"),
    ID_USER("IdUser"),
    SUCCESS_CREATE_LOBBI("SuccessCreateLobbi"),
    SUCCESS_CONNECTED_LOBBI("SuccessConnectedLobbi"),
    ERROR_CONNECTED_LOBBI("ErrorConnectedLobbi");

    private String string;

    CommandGwent(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
