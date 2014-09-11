json.array!(@reservations) do |reservation|
  json.extract! reservation, :id, :Email_id, :Train_id, :PNR, :Available_date, :Reservation_status, :Reservation_date
  json.url reservation_url(reservation, format: :json)
end
