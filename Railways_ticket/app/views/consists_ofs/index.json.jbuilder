json.array!(@consists_ofs) do |consists_of|
  json.extract! consists_of, :id, :station_ID, :train_ID, :stop_number
  json.url consists_of_url(consists_of, format: :json)
end
