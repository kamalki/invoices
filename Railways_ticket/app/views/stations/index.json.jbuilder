json.array!(@stations) do |station|
  json.extract! station, :id, :sation_id, :station_name
  json.url station_url(station, format: :json)
end
