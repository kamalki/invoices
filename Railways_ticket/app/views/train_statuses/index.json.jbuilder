json.array!(@train_statuses) do |train_status|
  json.extract! train_status, :id, :Train_id, :Available_date, :Booked_seat1, :Booked_seat2, :Booked_seat3, :Waiting_seat1, :Waiting_seat2, :Waiting_seat3, :Available_seat1, :Avalable_seat2, :Available_seat3
  json.url train_status_url(train_status, format: :json)
end
