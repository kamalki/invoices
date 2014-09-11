json.array!(@trains) do |train|
  json.extract! train, :id, :train_name, :train_type, :seats_class1, :seats_class2, :fare_class1, :fare_class2, :available_days
  json.url train_url(train, format: :json)
end
