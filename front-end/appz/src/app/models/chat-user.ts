import {Chat} from "./chat";

export interface ChatUser {
  receiverName: string;
  lastMessageSenderName: string;
  chat: Chat;
}
