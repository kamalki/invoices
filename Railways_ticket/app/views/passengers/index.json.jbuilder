json.array!(@passengers) do |passenger|
  json.extract! passenger, :id, :PNR, :passenger_name, :Gender, :Age, :Seat_no, :Class, :Fare, :Source_id, :Destination_id
  json.url passenger_url(passenger, format: :json)
end
