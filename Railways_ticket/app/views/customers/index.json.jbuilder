json.array!(@customers) do |customer|
  json.extract! customer, :id, :PNR, :passenger_name, :Gender, :Age, :Seat_no, :Class, :Fare, :Source_id, :Destination_id
  json.url customer_url(customer, format: :json)
end
