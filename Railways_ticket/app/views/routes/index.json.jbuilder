json.array!(@routes) do |route|
  json.extract! route, :id, :train_ID, :stop_number, :source_distace, :arrival_time, :departure_time
  json.url route_url(route, format: :json)
end
