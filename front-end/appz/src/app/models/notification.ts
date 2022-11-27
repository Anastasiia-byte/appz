export interface Notification {
  id: number;
  userId: number;
  messageId: number;
  read: boolean;
  senderFullName: string;
}
